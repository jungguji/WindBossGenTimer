<template>
  <div>
    <v-app>
      <AppBar />
      <v-content>
        <router-view :key="$route.fullPath" />
      </v-content>
    </v-app>
  </div>
</template>

<script>
import AppBar from "./components/AppBar";

export default {
  name: "App",
  components: {
    AppBar
  }
};
</script>
