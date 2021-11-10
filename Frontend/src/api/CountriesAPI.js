import axios from "axios";
import config from '../config/config'

export function getCountries(){
    return axios.get(`${config.BASE_URL}/countries`)
}
