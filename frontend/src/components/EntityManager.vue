<template>
  <div>
    <DxForm :form-data="formData" :col-count="2">
      <DxSimpleItem v-for="field in config.fields" :key="field.name" :data-field="field.name" :is-required="!field.optional" :editor-type="editorType(field)" :editor-options="editorOptions(field)" />
      <DxButtonItem :button-options="{ text: `${config.title} 등록`, type: 'success', onClick: submit }" />
    </DxForm>

    <DxDataGrid :data-source="rows" :show-borders="true" style="margin-top:20px;">
      <DxColumn v-for="col in config.columns" :key="col" :data-field="col" />
    </DxDataGrid>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref, watch } from 'vue'
import DxDataGrid, { DxColumn } from 'devextreme-vue/data-grid'
import DxForm, { DxSimpleItem, DxButtonItem } from 'devextreme-vue/form'
import { api } from '../api/client'

const props = defineProps({ config: Object, options: Object })
const emit = defineEmits(['refresh-all'])
const rows = ref([])
const formData = reactive({})

function editorType(field) {
  if (field.type === 'select' || field.type === 'enum') return 'dxSelectBox'
  return 'dxTextBox'
}

function editorOptions(field) {
  if (field.type === 'select') {
    return { dataSource: props.options[field.optionKey], displayExpr: 'name', valueExpr: 'id' }
  }
  if (field.type === 'enum') {
    return { dataSource: field.values }
  }
  return {}
}

async function load() {
  const { data } = await api.get(`/${props.config.key}`)
  rows.value = data
}

async function submit() {
  await api.post(`/${props.config.key}`, formData)
  Object.keys(formData).forEach((k) => (formData[k] = null))
  await load()
  emit('refresh-all')
}

watch(() => props.config.key, load)
onMounted(load)
</script>
