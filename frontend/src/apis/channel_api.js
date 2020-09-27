import { AXIOS } from "./http_common";

export default {
  requestChannels(id) {
    return AXIOS.get(`${process.env.VUE_APP_BASEURL}/dungeon/${id}`);
  }
};
