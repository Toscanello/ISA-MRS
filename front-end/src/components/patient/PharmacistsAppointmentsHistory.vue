<template>
  <v-data-table
    :headers="headers"
    :items="appointments"
    class="elevation-1"
  >
    <template v-slot:item.startTime="{ item }">

        {{ item.startTime.split("T")[0]}} {{ item.startTime.split("T")[1]}}
    </template>
  </v-data-table>
</template>

<script>
  import TokenDecoder from '../../services/token-decoder'
  import axios from "axios";
  export default {
    name: "DermatologistAppointment",
    data: () => ({
      dialog: false,
      dialogDelete: false,
      headers: [
        {
          text: 'Pharmacist name',
          align: 'start',
          value: 'medicalWorker.name',
        },
        { text: 'Dermatologist surname', value: 'medicalWorker.surname' },
        { text: 'Dermatologist email', value: 'medicalWorker.email' },
        { text: 'Price', value: 'price' },
        { text: 'Date and Time', value: 'startTime' },
      ],
      appointments: [],
      selectedItem: null,
      editedItem: {
       id:null,startTime:"",endTime:"",patient:
       {email:"",password:"",name:"",surname:"",
       address:{id:null,street:"",streetNumber:"",place:"",country:"",
       location:{geoWidth:null,geoHeight:null}},phoneNumber:""},
       medicalWorker:{email:"",password:"",name:"",surname:"",phoneNumber:"",role:{name:""}},price:null,
       address:{id:null,street:"",streetNumber:"",place:"",country:"",location:{geoWidth:null,geoHeight:null}}},
      defaultItem: {
       id:null,startTime:"",endTime:"",patient:
       {email:"",password:"",name:"",surname:"",
       address:{id:null,street:"",streetNumber:"",place:"",country:"",
       location:{geoWidth:null,geoHeight:null}},phoneNumber:""},
       medicalWorker:{email:"",password:"",name:"",surname:"",phoneNumber:"",role:{name:""}},price:null,
       address:{id:null,street:"",streetNumber:"",place:"",country:"",location:{geoWidth:null,geoHeight:null}}},
    }),


    watch: {
      dialogDelete (val) {
        val || this.closeDelete()
      },
    },

     created () {
            let usersEmail = TokenDecoder.getUserEmail()
            let path = "http://localhost:9090/patients/appointments/pharmacist/" + usersEmail ;
            axios.get(path).then((response) => {
                this.appointments = response.data;
            })
    },

    methods: {
        getColor (calories) {
        if (calories > 400) return 'red'
        else if (calories > 200) return 'orange'
        else return 'green'
      },
    },
  }
</script>