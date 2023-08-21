<script setup>
import { reactive } from 'vue';
import HelloWorld from './components/HelloWorld.vue'
import WatchTestView from './views/WatchTestView.vue'
import TeleportA from "@/views/teleport/TeleportA.vue";
import Parent from "@/views/sendProps/Parent.vue";
import CounterView from "@/views/pinia/CounterView.vue";
// ref
let count = ref(0)
let addRef = () => {
  ++ count.value
}

// reactive
let countReactive = reactive({
  count: 100
})
let addReactive = () => {
  ++ countReactive.count
}

// toRefs
let countToRefs = reactive({
  countToRefsValue: 100
})

// countToRefsValue 要与 对象中的 key 相同
let {countToRefsValue} = toRefs(countToRefs)
let addToRefs = () => {
  ++ countToRefsValue.value
}

// computed
let computedValueRaw = ref("这里是computedValueRaw")

let changeRawValue = () => {
  computedValueRaw.value = computedValueRaw.value + "再次 changed"
}

// 只读，不能修改?? 我测试了可以修改
let computedValue = computed(() => {
  return computedValueRaw.value + "修改后"
})

// 可以修改的写法
// let computedValueCanUpdate = computed({
//   get() {
//       return computedValueRaw.value;
//   },
//   set(val) {
//     return computedValueRaw.value = val
//   }
// })

</script>

<template>
<!--  <h1>ref</h1>-->
<!--  <button @click= "addRef">使用 ref：{{count}}</button>-->

<!--  <h1>reactive</h1>-->
<!--  <button @click= "addReactive">使用 reactive{{countReactive}}</button>-->

<!--  <h1>toRefs</h1>-->
<!--  <button @click= "addToRefs">使用 toRefs：{{countToRefsValue}}</button>-->

<!--  <h1>computed</h1>-->
<!--  <button @click="changeRawValue">{{ computedValue }}</button>-->

  <br/>
<!--  <WatchTestView />-->
  
<!--  <RouterView/>-->
  <h1>teleport</h1>
  <TeleportA/>

  <h1>sendProps</h1>
  <Parent/>
  <h1>pinia test</h1>
  <CounterView/>

</template>