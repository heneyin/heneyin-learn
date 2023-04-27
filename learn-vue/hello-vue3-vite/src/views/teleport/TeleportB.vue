<!--将内容传送到另外一个地方-->
<!--这个是子组件-->
<template>
  <div> 子组件 B </div>
  <!--  传送到 body-->
  <teleport to="body">
    <div>这是传送，我传送到了 body 里</div>
  </teleport>

  <!--  传送到 class 处-->
  <teleport to=".main-A">
    <div>这是传送，我传送到了 class main-A  里</div>
  </teleport>

  <!--  传送到 id 处-->
  <teleport to="#container-A">
    <div>这是传送，我传送到了 id container-A  里</div>
  </teleport>
</template>

