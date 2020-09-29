import Vue from "vue";
import Vuex from "vuex";
import bosses from "./channel";

Vue.use(Vuex);

export default new Vuex.Store({
  modules: {
    bosses
  }
});
