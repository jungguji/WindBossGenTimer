import { requestBoss } from "../apis/channel_api.js";

const state = {
  bosses: []
};

const mutations = {
  GET_BOSS_LIST(state, response) {
    state.bosses = response;
  }
};

const actions = {
  async QUERY_BOSS(context, params) {
    try {
      console.log("actions");
      console.log(params.dungeonId);
      console.log(params.channelId);
      const response = await requestBoss(params);
      console.log(response.data);
      context.commit("GET_BOSS_LIST", response.data);
      return response.data;
    } catch (e) {
      alert(e, e.response);
    }
  }
};

export default { mutations, state, actions };
