<template>
  <div>
    <!-- 使用父组件传递过来的值 -->
    <h1>子组件的值：{{ props.value }}</h1>
    <div>修改</div>
    <input v-model="props.value">
  </div>

</template>
<script setup>
// 从 vue 中引入defineProps
import { defineProps } from 'vue'

// 接收父组件传递过来的值
const props = defineProps(['value'])

</script>