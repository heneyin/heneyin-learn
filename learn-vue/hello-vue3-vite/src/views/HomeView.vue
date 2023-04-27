<!-- <script setup>
import TheWelcome from '../components/TheWelcome.vue'
</script> -->

<!-- <template>
  <main>
    <TheWelcome />
  </main>
</template> -->

<script setup>
import { useRouter } from 'vue-router';

// 使用代码方式跳转
let router = useRouter();
let goAbout = () => {
  router.push("/about")
}
</script>


<template>
  <div>首页</div>
  <button @click="goAbout">跳转到关于</button>
  <RouterLink to="/about">跳转到关于</RouterLink>
</template>
