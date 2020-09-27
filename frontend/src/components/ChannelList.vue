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
                  <v-btn large color="primary">
                    {{ subChannel.subChannel }}</v-btn
                  >
                </div>
              </v-card-actions>
            </v-list-item-content>
          </v-list-item>
        </v-card>
      </v-row>
    </v-container>
  </div>
</template>

<script>
import api from "../apis/channel_api.js";

export default {
  name: "ChannelList",
  data: function() {
    return {
      channels: []
    };
  },
  mounted() {
    let id = this.$route.params.id;
    api
      .requestChannels(id)
      .then(response => {
        this.channels = response.data;
        console.log(response.data);
      })
      .catch(e => {
        this.errors.push(e);
      });
  }
};
</script>
