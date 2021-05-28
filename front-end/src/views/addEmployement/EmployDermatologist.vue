<template>
      <div>
        <pharmacy-admin-home></pharmacy-admin-home>
        <v-row justify="center">
            <v-col cols="12" sm="10" md="8" lg="8">
                <v-data-table
                    :headers="headers"
                    :items="dermatologists"
                    :items-per-page="10"
                    class="elevation-1"
                >
                    <template v-slot:item.actions="{ item }">
                        <v-icon small class="mr-2" @click="employ(item)">
                            mdi-account-plus
                        </v-icon>
                    </template>
                </v-data-table>
            </v-col>
        </v-row>
    </div>
</template>

<script>
import axios from 'axios'
import PharmacyAdminHome from '../PharmacyAdminHome.vue'
export default {
  components: { PharmacyAdminHome },
    name: 'EmployDermatologist',
    data() {
        return {
            dermatologists: [],
            headers: [
                {text:'Name', value: 'name'},
                {text:'Surname', value: 'surname'},
                {text:'Email', value: 'email'},
                {text:'Employ', value: 'actions'}
            ]
        }
    },
    mounted() {
        axios
        .get('http://localhost:9090/api/dermatologists/list/all')
        .then(response => {
            this.dermatologists = response.data
        })
        .catch(error => {
            alert(error.response.data)
        })
    },
    methods: {
        employ(dermatologist) {
            let path = '/employ/dermatologist/' + this.$route.params.regNo + '/' + dermatologist.email
            this.$router.push(path)
        }
    }
}
</script>

<style>

</style>