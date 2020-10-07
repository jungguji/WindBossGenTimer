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
    <v-bottom-sheet v-model="sheet" persistent>
      <v-sheet class="text-center" height="500px">
        <v-btn class="mt-6" text color="red" @click="screenClose">
          close
        </v-btn>
        <div class="py-3">
          <v-data-table
            :headers="headers"
            :items="bosses.bosses"
            :items-per-page="5"
            class="elevation-1"
          >
            <template v-slot:[`item.remainTime`]="{ item }">
              <div class="my-cursor">
                <span> {{ remain[item.rowId].minute }} </span> :
                <span> {{ remain[item.rowId].second }} </span>
                <span class="ml-1" style="display: none">
                  {{ item.remainTime }}
                </span>
              </div>
            </template>
            <template v-slot:[`item.killTime`]="props">
              <td class="text-xs-right">
                <v-edit-dialog
                  :return-value.sync="props.item.realKillTime"
                  lazy
                  large
                  persistent
                  @save="
                    save(
                      props.item.killTimeId,
                      props.item.bossId,
                      props.item.realKillTime
                    )
                  "
                  @cancel="cancel"
                  @open="open"
                  @close="close"
                >
                  {{ props.item.realKillTime }}
                  <template v-slot:[`input`]>
                    <v-text-field
                      v-model="props.item.realKillTime"
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
      ],
      polling: [],
      remain: [{ hour: "00", minute: "00", second: "00" }]
    };
  },
  methods: {
    getBoss(dungeonId, channelId) {
      this.sheet = !this.sheet;
      this.$store.dispatch("QUERY_BOSS", { dungeonId, channelId });

      let genTime = [];
      let killTime = [];

      const length = this.$store.state.bosses.bosses.length;

      for (let i = 0; i < length; i++) {
        genTime[i] = this.$store.state.bosses.bosses[i].genTime;
        killTime[i] = this.$store.state.bosses.bosses[i].killTime;

        let current = new Date();
        current.setSeconds(
          killTime[i].second - current.getSeconds() + genTime[i].second
        );

        current.setMinutes(
          killTime[i].minute - current.getMinutes() + genTime[i].minute
        );

        current.setHours(
          killTime[i].hour - current.getHours() + genTime[i].hour
        );

        const calculatedTime = {
          hour: current.getHours(),
          minute: current.getMinutes(),
          second: current.getSeconds()
        };

        this.remain[i] = calculatedTime;
      }

      for (let i = 0; i < length; i++) {
        this.polling[i] = setInterval(() => this.countdown(i), 1000);
      }
    },
    countdown: function(i) {
      if (this.remain[i].second >= 1) {
        this.remain[i].second--;
        this.$set(this.remain[i], "second", this.remain[i].second);

        console.log(this.remain[i].second);
      } else {
        if (this.remain[i].minute >= 0) {
          this.remain[i].minute--;
          this.$set(this.remain[i], "minute", this.remain[i].minute);
          this.remain[i].second = 59;
        } else {
          this.remain[i].second = 0;
          this.timeStop(i);
        }
      }

      this.$set(this.remain, i, this.remain[i]);
    },
    timeStop(i) {
      clearInterval(this.polling[i]);
    },
    padTime: function(time) {
      return (time < 10 ? "0" : "") + time;
    },
    save(killTimeid, bossId, killTime) {
      this.$store.dispatch("UPDATE_KILLTIME", { killTimeid, bossId, killTime });

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
    },
    screenClose() {
      this.sheet = !this.sheet;
      for (let i = 0; i < this.remain.length; i++) {
        this.timeStop(i);
      }
    }
  },
  mounted() {
    let id = this.$route.params.id;
    requestChannels(id)
      .then(response => {
        this.channels = response.data;
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
