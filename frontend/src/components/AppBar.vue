<template>
  <nav>
    <v-navigation-drawer
      v-model="drawer"
      :clipped="$vuetify.breakpoint.lgAndUp"
      app
    >
      <v-list dense>
        <template v-for="item in items">
          <v-list-group
            :key="item.name"
            v-model="item.model"
            :prepend-icon="item.model ? chevronUp : chevronDown"
            append-icon=""
          >
            <template v-slot:activator>
              <v-list-item-content>
                <v-list-item-title>
                  {{ item.name }}
                </v-list-item-title>
              </v-list-item-content>
            </template>
            <v-list-item
              v-for="dungeon in item.dungeons"
              :key="dungeon.name"
              @click="requestChannels(dungeon.id)"
            >
              <v-icon> mdi-alpha-d-circle-outline </v-icon>
              <v-list-item-content>
                <v-list-item-title>
                  {{ dungeon.name }}
                </v-list-item-title>
              </v-list-item-content>
            </v-list-item>
          </v-list-group>
        </template>
      </v-list>
    </v-navigation-drawer>

    <v-app-bar
      :clipped-left="$vuetify.breakpoint.lgAndUp"
      app
      color="blue darken-3"
      dark
    >
      <v-app-bar-nav-icon @click.stop="drawer = !drawer"></v-app-bar-nav-icon>
      <v-toolbar-title style="width: 300px" class="ml-0 pl-4">
        <span class="hidden-sm-and-down">바람의 나라:연 보스 젠 타이머</span>
      </v-toolbar-title>
      <v-spacer></v-spacer>
      <v-btn icon>
        <v-icon>mdi-apps</v-icon>
      </v-btn>
      <v-btn icon>
        <v-icon>mdi-bell</v-icon>
      </v-btn>
      <v-btn icon large>
        <v-avatar size="32px" item>
          <v-img
            src="https://cdn.vuetifyjs.com/images/logos/logo.svg"
            alt="Vuetify"
          ></v-img
        ></v-avatar>
      </v-btn>
    </v-app-bar>
  </nav>
</template>

<script>
import api from "../apis/main_api.js";

export default {
  props: {
    source: String
  },
  data: () => ({
    dialog: false,
    drawer: null,
    chevronUp: "mdi-chevron-up",
    chevronDown: "mdi-chevron-down",
    items: [
      {
        model: false,
        dungeons: []
      }
    ]
  }),
  methods: {
    requestChannels(id) {
      this.$router.push(`/dungeon/${id}`);
    }
  },
  mounted() {
    api
      .requestMainView()
      .then(response => {
        this.items = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  }
};
</script>
