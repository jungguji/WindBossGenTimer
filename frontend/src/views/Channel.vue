<template>
  <div class="channels">
    <ChannelList />
  </div>
</template>

<script>
// @ is an alias to /src
import ChannelList from "@/components/ChannelList.vue";

export default {
  name: "Channel",
  components: { ChannelList }
};
</script>
