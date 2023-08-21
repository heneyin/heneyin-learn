<template>
  <div>
    <!-- 通过自定义属性传递值 -->
    <div>父组件的值：{{doc}}</div>
    <ChildComopment :value="doc"/>
  </div>
</template>
<script setup>
import { ref } from 'vue'

import ChildComopment from "@/views/sendProps/ChildComopment.vue";

// 待传递的值
const doc = ref('hello');

</script>
