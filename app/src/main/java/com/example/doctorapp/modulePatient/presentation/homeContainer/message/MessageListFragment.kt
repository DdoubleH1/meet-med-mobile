package com.example.doctorapp.modulePatient.presentation.homeContainer.message

import android.os.Bundle
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.doctorapp.R
import com.example.doctorapp.data.model.MessageRoom
import com.example.doctorapp.data.model.User
import com.example.doctorapp.databinding.FragmentMessageListBinding
import com.example.doctorapp.domain.core.base.BaseFragment
import com.example.doctorapp.modulePatient.presentation.adapter.MessageRoomAdapter
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MessageListFragment :
    BaseFragment<FragmentMessageListBinding, MessageListViewModel>(R.layout.fragment_message_list) {

    companion object {
        fun newInstance() = MessageListFragment()
    }

    private val viewModel: MessageListViewModel by viewModels()
    override fun getVM() = viewModel
    private var messageRoomAdapter: MessageRoomAdapter? = null

    override fun initView(savedInstanceState: Bundle?) {
        super.initView(savedInstanceState)
        messageRoomAdapter = MessageRoomAdapter(context = requireContext())
        messageRoomAdapter?.submitList(generateMessageRooms())
        binding.rvMessageList.apply {
            adapter = messageRoomAdapter
            layoutManager = LinearLayoutManager(context)
        }
    }


    private fun generateMessageRooms(): List<MessageRoom> {
        val users = listOf(
            User(id = "1", fullName = "Dr. Emily Walker"),
            User(id = "2", fullName = "Dr. John Smith"),
            User(id = "3", fullName = "Dr. Alice Johnson"),
            User(id = "4", fullName = "Dr. Robert Brown"),
            User(id = "5", fullName = "Dr. Michael Davis"),
            User(id = "6", fullName = "Dr. Sarah Wilson"),
            User(id = "7", fullName = "Dr. David Martinez"),
            User(id = "8", fullName = "Dr. Linda Anderson"),
            User(id = "9", fullName = "Dr. James Thomas"),
            User(id = "10", fullName = "Dr. Patricia Jackson")
        )

        return listOf(
            MessageRoom(
                id = "1",
                status = "unread",
                lastSentMessageContent = "Hello!",
                lastSentTimeStamp = "08:00",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[0]
            ),
            MessageRoom(
                id = "2",
                status = "unread",
                lastSentMessageContent = "How are you?",
                lastSentTimeStamp = "08:05",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[1]
            ),
            MessageRoom(
                id = "3",
                status = "read",
                lastSentMessageContent = "Good morning",
                lastSentTimeStamp = "08:10",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[2]
            ),
            MessageRoom(
                id = "4",
                status = "unread",
                lastSentMessageContent = "See you soon",
                lastSentTimeStamp = "08:15",
                lastSentMessageType = "image",
                type = "chat",
                sender = users[3]
            ),
            MessageRoom(
                id = "5",
                status = "read",
                lastSentMessageContent = "Take care",
                lastSentTimeStamp = "08:20",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[4]
            ),
            MessageRoom(
                id = "6",
                status = "unread",
                lastSentMessageContent = "Thank you",
                lastSentTimeStamp = "08:25",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[5]
            ),
            MessageRoom(
                id = "7",
                status = "read",
                lastSentMessageContent = "Goodbye",
                lastSentTimeStamp = "08:30",
                lastSentMessageType = "video",
                type = "chat",
                sender = users[6]
            ),
            MessageRoom(
                id = "8",
                status = "unread",
                lastSentMessageContent = "Yes, sure",
                lastSentTimeStamp = "08:35",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[7]
            ),
            MessageRoom(
                id = "9",
                status = "read",
                lastSentMessageContent = "No problem",
                lastSentTimeStamp = "08:40",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[8]
            ),
            MessageRoom(
                id = "10",
                status = "unread",
                lastSentMessageContent = "See you later",
                lastSentTimeStamp = "08:45",
                lastSentMessageType = "text",
                type = "chat",
                sender = users[9]
            )
        )
    }
}