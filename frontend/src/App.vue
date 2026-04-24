<template>
  <div class="container">
    <el-card class="box-card">
      <template #header>
        <div class="card-header">
          <span>🌟 Life Tracker - 我的生活记录</span>
        </div>
      </template>

      <!-- 输入区域 -->
      <div class="input-section">
        <el-input
          v-model="newActivity.content"
          placeholder="今天发生了什么有趣的事？"
          class="input-with-select"
        >
          <template #prepend>
            <el-select v-model="newActivity.category" placeholder="分类" style="width: 100px">
              <el-option label="心情" value="心情" />
              <el-option label="工作" value="工作" />
              <el-option label="学习" value="学习" />
              <el-option label="运动" value="运动" />
              <el-option label="美食" value="美食" />
            </el-select>
          </template>
          <template #append>
            <el-button @click="addActivity" type="primary">记录一下</el-button>
          </template>
        </el-input>
      </div>

      <!-- 列表区域 -->
      <el-table :data="activities" style="width: 100%; margin-top: 20px" v-loading="loading">
        <el-table-column prop="createTime" label="时间" width="180">
          <template #default="scope">
            {{ formatTime(scope.row.createTime) }}
          </template>
        </el-table-column>
        <el-table-column prop="category" label="分类" width="100">
          <template #default="scope">
            <el-tag :type="getTagType(scope.row.category)">{{ scope.row.category }}</el-tag>
          </template>
        </el-table-column>
        <el-table-column prop="content" label="内容" />
        <el-table-column label="操作" width="100">
          <template #default="scope">
            <el-button size="small" type="danger" @click="deleteActivity(scope.row.id)">删除</el-button>
          </template>
        </el-table-column>
      </el-table>
    </el-card>
  </div>
</template>

<script setup>
import { ref, onMounted } from 'vue'
import axios from 'axios'
import { ElMessage } from 'element-plus'

const activities = ref([])
const loading = ref(false)
const newActivity = ref({
  content: '',
  category: '心情'
})

const API_URL = 'http://localhost:8081/api/activities'

// 获取列表
const fetchActivities = async () => {
  loading.ref = true
  try {
    const res = await axios.get(API_URL)
    activities.value = res.data
  } catch (error) {
    ElMessage.error('获取记录失败，请确保后端服务已启动')
  } finally {
    loading.ref = false
  }
}

// 添加记录
const addActivity = async () => {
  if (!newActivity.value.content) {
    ElMessage.warning('内容不能为空哦')
    return
  }
  try {
    await axios.post(API_URL, newActivity.value)
    ElMessage.success('记录成功！')
    newActivity.value.content = ''
    fetchActivities()
  } catch (error) {
    ElMessage.error('记录失败')
  }
}

// 删除记录
const deleteActivity = async (id) => {
  try {
    await axios.delete(`${API_URL}/${id}`)
    ElMessage.success('已删除')
    fetchActivities()
  } catch (error) {
    ElMessage.error('删除失败')
  }
}

// 格式化时间
const formatTime = (timeStr) => {
  if (!timeStr) return ''
  const date = new Date(timeStr)
  return date.toLocaleString()
}

// 标签样式
const getTagType = (category) => {
  const map = {
    '心情': 'success',
    '工作': 'warning',
    '学习': 'primary',
    '运动': 'danger',
    '美食': 'info'
  }
  return map[category] || ''
}

onMounted(fetchActivities)
</script>

<style scoped>
.container {
  max-width: 900px;
  margin: 40px auto;
  padding: 0 20px;
}
.card-header {
  font-size: 20px;
  font-weight: bold;
  color: #409eff;
}
.input-section {
  margin-bottom: 30px;
}
</style>
