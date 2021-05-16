<template>
<div>
    <pharmacy-admin-home></pharmacy-admin-home>
    <v-row justify="center">
        <v-col cols="12" sm="10" md="8" lg="8">
            <v-data-table
                :headers="headers"
                :items="requests"
                :items-per-page="10"
                @click:row = "viewRequest"
                class="elevation-1"
            ></v-data-table>
        </v-col>
    </v-row>
</div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
import authHeader from '../../services/auth-header'
export default {
  components: { PharmacyAdminHome },
    name: 'VacationRequestTable',
    data() {
        return {
            requests: [],
            headers: [
                {
                    text: 'Employee',
                    value: 'medicalWorkerId'
                },
                {
                    text: 'Vacation start',
                    value: 'startDate'
                },
                {
                    text: 'Vacation end',
                    value: 'endDate'
                }
            ]
        }
    },
    mounted() {
        let path = 'http://localhost:9090/api/vaccation/pharmacist/requests/' + this.$route.params.regNo
        axios
        .get(path, { headers: authHeader() })
        .then(response => {
            this.requests = response.data
        })
        .catch(error => {
            alert(error.response.data)
        })
    },
    methods: {
        viewRequest(data) {
            this.$router.push('/vacation/response/' + data.medicalWorkerId)
        }
    }
}
</script>
    
<style>

</style>