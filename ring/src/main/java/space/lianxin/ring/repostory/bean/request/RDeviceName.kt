package space.lianxin.ring.repostory.bean.request

data class RDeviceName(
    val approach_charge_account_id_un: Int? = null,
    val approach_equip_type: Int? = null,
    val approach_names: String? = null,
    val approach_search: String? = null,
    val approach_state: Int? = null,
    val approach_states: String? = null,
    val approach_type: Int? = null,
    val organ_ids: String? = "1",
    val page: Int? = 0,
    val page_size: Int? = 20
)