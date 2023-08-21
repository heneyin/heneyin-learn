import {defineStore} from "pinia";
import {computed, reactive, ref} from "vue";


export const useCounterStore = defineStore('demo', () => {
    const counter = ref(0)
    // 持久化后，ref 方式的数据刷新后不会丢失，仍可恢复。
    const arrRef = ref([])
    // 即使是持久化了，reactive 方式的数据，刷新后仍会丢失
    const arrReactive = reactive([])

    const increment = () => {
        counter.value++
    }

    return {
        counter,
        arrRef,
        arrReactive,
        increment
    }

},{
    persist: true
})

// 组合式
// export const useCounterStore = defineStore('counter', {
//     state: () => ({ count: 0, name: 'Eduardo' }),
//     getters: {
//         doubleCount: (state) => state.count * 2,
//     },
//     actions: {
//         increment() {
//             this.count++
//         },
//     },
// })