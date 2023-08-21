<template>
  <div class="about" style="background-color: bisque">
    <h1>This is an pinia test page</h1>
    <h3>counter: {{counter}}</h3>
    <button @click="add">加一</button>
    <br>
    <h3>arrRef: {{arrRef}}</h3>
    <button @click="pushArrRef">添加到数组 ref </button>
    <h3>arrReactive: {{arrReactive}}</h3>
    <button @click="pushArrReactive">添加到数组 Reactive </button>

    <h1>测试不存储为ref，直接赋值</h1>
    <h3>counter: {{counter1}}</h3>
    <button @click="add">加一</button>
    <br>
    <h3>arrRef: {{arrRef1}}</h3>
    <button @click="pushArrRef">添加到数组 ref </button>
    <h3>arrReactive: {{arrReactive1}}</h3>
    <button @click="pushArrReactive">添加到数组 Reactive </button>
  </div>
</template>

<script setup>
import {useCounterStore} from "@/store/counter";
import { storeToRefs } from 'pinia'

const demoStore = useCounterStore()
// 存储为 ref
const { counter, arrRef, arrReactive } = storeToRefs(demoStore)

const add = () => {
  demoStore.increment()
}

const pushArrRef = () => {
  arrRef.value.push({
    date: new Date()
  })
}

const pushArrReactive = () => {
  arrReactive.value.push({
    date: new Date()
  })
}

// 不存储为ref
const counter1 = demoStore.counter
const arrRef1 = demoStore.arrRef
const arrReactive1 = demoStore.arrReactive

</script>
<style scoped>
</style>