<template>
  <div>
      <pharmacy-admin-home></pharmacy-admin-home>
      <v-row justify="center">
          <v-col cols="12" sm="10" md="8" lg="8">
              <v-data-table
                :headers="headers"
                :items="orders"
                :items-per-page="10"
                :expanded.sync="expanded"
                show-expand
                class="elevation-1"
              >
                <template v-slot:expanded-item="{ headers, item }">
                    <td :colspan="headers.length">
                    <v-simple-table>
                        <template v-slot:default>
                            <tbody>
                                <tr v-for="orderItem in item.bulkOrderItemList" 
                                  :key="orderItem.medicineCode">
                                    <td>{{orderItem.medicineName}}</td>
                                    <td>Amount: {{orderItem.amount}}</td>
                                </tr>
                            </tbody>
                        </template>
                    </v-simple-table>
                    </td>
                </template>
                <template v-slot:item.actions="{ item }">
                    <v-icon small class="mr-2" @click="deleteOrder(item)">
                        mdi-delete
                    </v-icon>
                    <v-icon small class="mr-2" @click="viewOffers(item)">
                        mdi-eye
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
import authHeader from '../../services/auth-header'
export default {
  components: { PharmacyAdminHome },
    name: 'OrdersList',
    data() {
        return {
            orders: [],
            headers: [
                {text: 'Order ID', value: 'id'},
                {text: 'Due date', value: 'dueDate'},
                {text: 'Actions', value: 'actions'}
            ],
            expanded: [],
        }
    },
    mounted() {
        let path = 'http://localhost:9090/api/order/all/' + this.$route.params.regNo
        axios
        .get(path, { headers: authHeader() })
        .then(response => {
            this.orders = response.data
        })
        .catch(error => {
            alert(error.response.data)
        })
    },
    methods: {
        viewOffers(data) {
            this.$router.push('/offers/' + data.id)
        },
        deleteOrder(order) {
            if (Date.parse(order.dueDate) < Date.now()) {
                alert('Datum za izmenu narudzbine je prosao')
                return
            }
            let path = 'http://localhost:9090/api/order/delete/' + order.id
            axios
                .delete(path, { headers: authHeader() })
                .then(response => {
                    console.log(response)
                    alert('Uspesno izbrisana narudzbina')
                    this.$router.go()
                })
                .catch(error => {
                    alert(error.message)
                })
        }
    }
}
</script>

<style>

</style>