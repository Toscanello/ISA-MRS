<template>
  <v-container>
    <v-row justify="center" >
        <v-col>
            <input type="number" v-model="drug" placeholder="enter id">
        </v-col>
        <v-col>
            <v-btn :disabled="drug==''" @click="onClick()">
                Dispense
            </v-btn>
        </v-col>
    </v-row>
    <v-data-table
      dense
      :headers="headers"
      :items="items"
      item-key="name"
      class="elevation-1"
    ></v-data-table>
  </v-container>
</template>
<script>
import axios from "axios";
import authHeader from '../services/auth-header';
export default {
  data() {
    return {
        drug: "",
      items: [],
      headers: [
        {
          text: "Id",
          align: "start",
          sortable: false,
          value: "id",
        },
        { text: "Quantity", value: "quantity" },
        { text: "Price", value: "price" },
        { text: "Medicine", value: "medicine.medicineDTO.name" },
        { text: "Start time", value: "startTime", sortable: false },
        { text: "End time", value: "endTime", sortable: false },
      ],
    };
  },
  mounted() {
    axios
      .get("http://localhost:9090/api/medicine/getOrders/abc", {headers: authHeader()})
      .then((response) => (this.items = response.data));
  },
  methods:{
      onClick(){
          axios.put(`http://localhost:9090/api/medicine/update/${this.drug}`, null,{headers: authHeader()}).then(response=>{
              if(response.data!="OK")
                alert("Greska");
              window.location.reload();
              console.log(response.data)
              })
      }
  }
};
</script>