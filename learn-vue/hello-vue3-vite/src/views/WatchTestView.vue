<template>
    <h1>watchtest</h1>
    <input type="text" v-model="data">
    <button @click="data1.a = 2">修改data1</button>
</template>

<script setup>
import { watch } from 'vue';

let data = ref('数据')

// 监听一个 ref 数据
watch(data, (newVal, oldVal) => {
    console.log(oldVal, newVal)
})

let num = ref(1)

// 监听多个ref数据
watch([data, num], (newVal, oldVal) => {
    console.log(oldVal, newVal)
},{
    // 是否初始化是监听
    immediate: true
})

// 监听 reactive 数据

let data1 = reactive({
    a: 1
})
// 新数据，旧数据一样
watch(data1, (newVal, oldVal) => {
    console.log(oldVal, newVal)
},{
    // 是否初始化是监听
    immediate: true
})

// 立刻监听
watchEffect(() => {
    console.log(data.value)
})
</script>