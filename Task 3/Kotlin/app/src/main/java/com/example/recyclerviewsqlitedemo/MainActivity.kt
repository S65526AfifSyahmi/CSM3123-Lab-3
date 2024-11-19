package com.example.recyclerviewsqlitedemo

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView

class MainActivity : AppCompatActivity() {
    private lateinit var databaseHelper: DatabaseHelper
    private lateinit var userAdapter: UserAdapter
    private lateinit var recyclerView: RecyclerView
    private lateinit var btn_add_user: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        databaseHelper = DatabaseHelper(this)
        recyclerView = findViewById(R.id.recycler_view)
        btn_add_user = findViewById(R.id.btn_add_user)

        btn_add_user.setOnClickListener {
            showAddUserDialog()
        }

        setupRecyclerView()
        loadUserData()
    }

    private fun setupRecyclerView() {
        recyclerView.layoutManager = LinearLayoutManager(this)
    }

    private fun loadUserData() {
        val users = databaseHelper.getAllUsers()
        userAdapter = UserAdapter(users)
        recyclerView.adapter = userAdapter
    }

    private fun showAddUserDialog() {
        val builder = AlertDialog.Builder(this)
        builder.setTitle("Add User")

        val inflater = layoutInflater
        val dialogLayout = inflater.inflate(R.layout.dialog_add_user, null)
        builder.setView(dialogLayout)

        val et_name = dialogLayout.findViewById<EditText>(R.id.et_name)
        val et_age = dialogLayout.findViewById<EditText>(R.id.et_age)

        builder.setPositiveButtonIcon("Add") { dialog, _ ->
            val name = et_name.text.toString()
            val age = et_age.text.toString().toIntOrNull()

            if (name.isNotEmpty() && age != null) {
                databaseHelper.addUser(name, age)
                loadUserData()
                Toast.makeText(this, "User added", Toast.LENGTH_SHORT).show()
            }
            else {
                Toast.makeText(this, "Invalid input", Toast.LENGTH_SHORT).show()
            }
            dialog.dismiss()
        }

        builder.setNavigationButton("Cancel") { dialog, _ ->
            dialog.dismiss()
        }

        builder.show()
    }
}