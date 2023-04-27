<!--将内容传送到另外一个地方-->
<!--这个是父亲组件-->
<template>
  <div class="main-A">
    这是 A 的 main
  </div>
  <div id="container-A">
    这是 A 的 container
  </div>
  <TeleportB/>
</template>

<script setup>
import TeleportB from "@/views/teleport/TeleportB.vue";
</script>