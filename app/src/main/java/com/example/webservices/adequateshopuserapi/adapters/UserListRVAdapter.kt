package com.example.webservices.adequateshopuserapi.adapters

//class UserListRVAdapter(private var userList: List<UserInfo>) :
//    RecyclerView.Adapter<UserListRVAdapter.ViewHolder>() {

//    inner class ViewHolder(binding: RowUserDataBinding) : RecyclerView.ViewHolder(binding.root) {
//        var imgView = binding.imgUser
//        var tvId = binding.tvUserId
//        var tvFirstName = binding.tvUserFirstName
//        var tvLastName = binding.tvUserLastName
//        var tvEmail = binding.tvUserEmail
//
//        init {
//            binding.root.setOnClickListener {
//                val context = binding.root.context
//                with(context) {
//                    startActivity(Intent(this, UserDetailActivity::class.java).apply {
//                        putExtra(Constants.Keys.BUNDLE_KEY_USER_ID, userList[adapterPosition].id)
//                    })
//                }
//            }
//        }
//    }
//
//    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
//        val view = LayoutInflater.from(parent.context)
//            .inflate(R.layout.row_user_data, parent, false)
//        val binding = RowUserDataBinding.bind(view)
//        return ViewHolder(binding)
//    }
//
//    override fun getItemCount(): Int {
//        return userList.count()
//    }
//
//    @SuppressLint("SetTextI18n")
//    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
//        Picasso.get().load(userList[position].avatar).into(holder.imgView)
//        holder.tvId.text = "ID: ${userList[position].id}"
//        holder.tvEmail.text = "Email: ${userList[position].email}"
//        holder.tvFirstName.text = "First Name: ${userList[position].firstName}"
//        holder.tvLastName.text = "Last Name: ${userList[position].lastName}"
//    }
//
//    fun submitUserList(list: List<UserData>) {
//        userList = list
//    }

//}