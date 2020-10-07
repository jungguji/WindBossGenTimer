import { AXIOS } from "./http_common";

function requestChannels(id) {
  return AXIOS.get(`${process.env.VUE_APP_BASEURL}/dungeon/${id}`);
}

function requestBoss(params) {
  return AXIOS.get(
    `${process.env.VUE_APP_BASEURL}/dungeon/${params.dungeonId}/${params.channelId}`
  );
}

function requestUpdateKillTime(args) {
  return AXIOS.put(
    `${process.env.VUE_APP_BASEURL}/dungeon/${args.dungeonId}/${args.channelId}`,
    {
      params: {
        killTimeId: args.killTimeid,
        bossId: args.bossId,
        killTime: args.killTime
      }
    }
  );
}

export { requestChannels, requestBoss, requestUpdateKillTime };
