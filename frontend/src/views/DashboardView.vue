<template>
  <div class="page">
    <h1>그룹사 시스템 관리 포털</h1>
    <DxTabPanel :data-source="tabs" :defer-rendering="false" item-title-template="title" item-template="content">
      <template #title="{ data }">{{ data.title }}</template>
      <template #content="{ data }">
        <EntityManager :config="data" @refresh-all="loadAll" :options="selectOptions" />
      </template>
    </DxTabPanel>
  </div>
</template>

<script setup>
import { onMounted, reactive, ref } from 'vue'
import DxTabPanel from 'devextreme-vue/tab-panel'
import EntityManager from '../components/EntityManager.vue'
import { api } from '../api/client'

const tabs = ref([
  { key: 'applications', title: '어플리케이션', columns: ['id', 'name', 'description'], fields: [{ name: 'name' }, { name: 'description' }] },
  { key: 'subsystems', title: '서브시스템', columns: ['id', 'name', 'application.id'], fields: [{ name: 'name' }, { name: 'applicationId', type: 'select', optionKey: 'applications' }] },
  { key: 'programs', title: '프로그램', columns: ['id', 'name', 'subsystem.id'], fields: [{ name: 'name' }, { name: 'subsystemId', type: 'select', optionKey: 'subsystems' }] },
  { key: 'menus', title: '메뉴', columns: ['id', 'name', 'program.id'], fields: [{ name: 'name' }, { name: 'programId', type: 'select', optionKey: 'programs' }] },
  { key: 'apis', title: 'API', columns: ['id', 'name', 'path', 'method', 'program.id'], fields: [{ name: 'name' }, { name: 'path' }, { name: 'method' }, { name: 'programId', type: 'select', optionKey: 'programs' }] },
  { key: 'permissions', title: '메뉴/API 권한', columns: ['id', 'code', 'name', 'menu.id', 'api.id'], fields: [{ name: 'code' }, { name: 'name' }, { name: 'menuId', type: 'select', optionKey: 'menus', optional: true }, { name: 'apiId', type: 'select', optionKey: 'apis', optional: true }] },
  { key: 'users', title: '내부/외부 사용자', columns: ['id', 'username', 'email', 'userType'], fields: [{ name: 'username' }, { name: 'email' }, { name: 'userType', type: 'enum', values: ['INTERNAL', 'EXTERNAL'] }] }
])

const selectOptions = reactive({ applications: [], subsystems: [], programs: [], menus: [], apis: [] })

async function loadAll() {
  const keys = ['applications', 'subsystems', 'programs', 'menus', 'apis']
  for (const key of keys) {
    const { data } = await api.get(`/${key}`)
    selectOptions[key] = data
  }
}

onMounted(loadAll)
</script>

<style scoped>
.page { padding: 20px; }
h1 { margin-bottom: 20px; }
</style>
