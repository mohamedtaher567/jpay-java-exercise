import axios from "axios";
import config from '../config/config'
 
export function getPhoneNumbers(filters){
    return axios.post(`${config.BASE_URL}/phoneNumbers`, filters);
}