import { AXIOS } from "./http_common";

function requestChannels(id) {
  return AXIOS.get(`${process.env.VUE_APP_BASEURL}/dungeon/${id}`);
}

function requestBoss(params) {
  return AXIOS.get(
    `${process.env.VUE_APP_BASEURL}/dungeon/${params.dungeonId}/${params.channelId}`
  );
}

export { requestChannels, requestBoss };
