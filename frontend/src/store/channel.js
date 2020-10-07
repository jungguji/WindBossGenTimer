import { requestBoss, requestUpdateKillTime } from "../apis/channel_api.js";

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
      const response = await requestBoss(params);
      context.commit("GET_BOSS_LIST", response.data);
      return response.data;
    } catch (e) {
      alert(e, e.response);
    }
  },

  async UPDATE_KILLTIME(context, params) {
    try {
      await requestUpdateKillTime(params);
    } catch (e) {
      alert(e, e.response);
    }
  }
};

export default { mutations, state, actions };
