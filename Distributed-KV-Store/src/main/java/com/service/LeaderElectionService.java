package com.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

@Service
public class LeaderElectionService {

    @Value("${raft.node-id}")
    private String nodeId;

    @Value("${raft.leader-id}")
    private String leaderId;

    public boolean isLeader() {
        boolean leader = nodeId.equals(leaderId);
        System.out.println("Is Leader? " + leader + " [Node ID: " + nodeId + ", Leader ID: " + leaderId + "]");
        return leader;
    }
}

