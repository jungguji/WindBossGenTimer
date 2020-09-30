<template>
  <div class="fill-height">
    <v-container class="fill-height">
      <v-row align="center" class="fill-height mx-auto" justify="center">
        <v-card
          class="mx-auto"
          max-width="344"
          outlined
          v-for="channel in channels"
          :key="channel.mainChannel"
        >
          <v-list-item three-line>
            <v-list-item-content>
              <div class="overline mb-4">{{ channel.mainChannel }}</div>
              <v-card-actions class="text-center">
                <div
                  class="my-2"
                  v-for="subChannel in channel.subChannels"
                  :key="subChannel.id"
                >
                  <v-btn
                    large
                    color="primary"
                    @click="getBoss($route.params.id, subChannel.id)"
                  >
                    {{ subChannel.subChannel }}</v-btn
                  >
                </div>
              </v-card-actions>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </v-row>
    </v-container>
    <v-bottom-sheet v-model="sheet">
      <v-sheet class="text-center" height="500px">
        <v-btn class="mt-6" text color="red" @click="sheet = !sheet">
          close
        </v-btn>
        <div class="py-3">
          <v-data-table
            :headers="headers"
            :items="bosses.bosses"
            :items-per-page="5"
            class="elevation-1"
          >
            <template v-slot:[`item.killTime`]="props">
              <td class="text-xs-right">
                <v-edit-dialog
                  :return-value.sync="props.item.killTime"
                  lazy
                  large
                  persistent
                  @save="save"
                  @cancel="cancel"
                  @open="open"
                  @close="close"
                >
                  {{ props.item.killTime }}
                  <template v-slot:[`input`]>
                    <v-text-field
                      v-model="props.item.killTime"
                      single-line
                      counter
                    ></v-text-field>
                  </template>
                </v-edit-dialog>
              </td>
            </template>
          </v-data-table>

          <v-snackbar v-model="snack" :timeout="3000" :color="snackColor">
            {{ snackText }}

            <template v-slot:action="{ attrs }">
              <v-btn v-bind="attrs" text @click="snack = false">
                Close
              </v-btn>
            </template>
          </v-snackbar>
        </div>
      </v-sheet>
    </v-bottom-sheet>
  </div>
</template>

<script>
import { requestChannels } from "../apis/channel_api";

export default {
  name: "ChannelList",
  data: function() {
    return {
      snack: false,
      snackColor: "",
      snackText: "",
      sheet: false,
      max25chars: v => v.length <= 25 || "Input too long!",
      channels: [],
      headers: [
        {
          text: "보스명",
          align: "start",
          sortable: false,
          value: "bossName"
        },
        { text: "남은 시간", value: "remainTime" },
        { text: "죽인 시간", value: "killTime" }
      ]
    };
  },
  methods: {
    getBoss(dungeonId, channelId) {
      this.sheet = !this.sheet;
      this.$store.dispatch("QUERY_BOSS", { dungeonId, channelId });
    },
    save() {
      this.snack = true;
      this.snackColor = "success";
      this.snackText = "Data saved";
    },
    cancel() {
      this.snack = true;
      this.snackColor = "error";
      this.snackText = "Canceled";
    },
    open() {
      this.snack = true;
      this.snackColor = "info";
      this.snackText = "Dialog opened";
    },
    close() {
      console.log("Dialog closed");
    }
  },
  mounted() {
    let id = this.$route.params.id;
    requestChannels(id)
      .then(response => {
        this.channels = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  },
  computed: {
    bosses() {
      return this.$store.state.bosses;
    }
  }
};
</script>
