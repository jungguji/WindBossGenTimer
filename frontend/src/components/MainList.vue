/* eslint-disable vue/require-v-for-key */
<template>
  <div class="layout">
    <h2>{{ title }}</h2>
    <div v-for="item in names" :key="item.name">
      <span> {{ item.name }} </span>
    </div>
  </div>
</template>

<script>
import api from "../apis/main_api.js";

export default {
  name: "MainList",
  data: function() {
    return {
      title: "던전 리스트",
      names: [],
      errors: []
    };
  },
  mounted() {
    api
      .requestMainView()
      .then(response => {
        this.names = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped>
</style>
