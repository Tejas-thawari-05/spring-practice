// SPDX-License-Identifier: MIT
pragma solidity ^0.8.0;

contract PostStorage {
    
    struct Post {
        uint id;
        string postHash;
        address user;
        uint timestamp;
    }

    uint private counter = 0;
    mapping(uint => Post) private posts;

    event PostStored(uint id, string postHash, address indexed user, uint timestamp);

    function storePostHash(string memory _postHash) public {
        posts[counter] = Post(counter, _postHash, msg.sender, block.timestamp);
        emit PostStored(counter, _postHash, msg.sender, block.timestamp);
        counter++;
    }

    function getPostById(uint _id) public view returns (string memory, address, uint) {
        Post memory post = posts[_id];
        return (post.postHash, post.user, post.timestamp);
    }

    function getTotalPosts() public view returns (uint) {
        return counter;
    }
}
