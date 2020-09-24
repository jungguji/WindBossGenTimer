/* eslint-disable vue/require-v-for-key */
<template>
  <div class="layout">
    <h2>{{ title }}</h2>
    <div>
      <div>
        <b-button
          variant="outline-primary"
          v-for="dungeon in dungeons"
          :key="dungeon.name"
          :class="is_show ? null : 'collapsed'"
          :aria-expanded="is_show ? 'true' : 'false'"
          aria-controls="collapse-1"
          @click="popupChannel()"
          >{{ dungeon.name }}</b-button
        >
      </div>
    </div>
    <!-- #2 : Modal Window -->
    <b-collapse id="collapse-1" v-model="is_show" class="mt-2">
      <div class="fill-height">
        <v-container class="fill-height">
          <v-row align="center" class="fill-height mx-auto" justify="center">
                <v-card class="pa-3 mx- 3 boards-list" dark>
                    <v-card-title>
                    </v-card-title>
                </v-card>
          </v-row>
        </v-container>
      </div>
      <b-card>I am collapsible content!</b-card>
    </b-collapse>
  </div>
</template>

<script>
import api from "../apis/main_api.js";

export default {
  name: "MainList",
  data: function() {
    return {
      is_show: false,
      title: "던전 리스트",
      dungeons: [],
      errors: []
    };
  },
  methods: {
    popupChannel() {
      this.is_show = !this.is_show;


      //      this.$router.push(`dungeon/${id}`);
    }
  },
  mounted() {
    api
      .requestMainView()
      .then(response => {
        this.dungeons = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  }
};
</script>

<!-- Add "scoped" attribute to limit CSS to this component only -->
<style scoped></style>
