package com.service;

import java.math.BigInteger;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import org.web3j.abi.TypeReference;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.Function;
import org.web3j.abi.datatypes.Type;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.crypto.Credentials;
import org.web3j.protocol.Web3j;
import org.web3j.protocol.core.RemoteCall;
import org.web3j.protocol.core.RemoteFunctionCall;
import org.web3j.protocol.core.methods.response.TransactionReceipt;
import org.web3j.tuples.generated.Tuple2;
import org.web3j.tx.Contract;
import org.web3j.tx.TransactionManager;
import org.web3j.tx.gas.ContractGasProvider;

/**
 * <p>Auto generated code.
 * <p><strong>Do not modify!</strong>
 * <p>Please use the <a href="https://docs.web3j.io/command_line.html">web3j command line tools</a>,
 * or the org.web3j.codegen.SolidityFunctionWrapperGenerator in the 
 * <a href="https://github.com/hyperledger-web3j/web3j/tree/main/codegen">codegen module</a> to update.
 *
 * <p>Generated with web3j version 1.6.3.
 */
@SuppressWarnings("rawtypes")
public class PostStorage extends Contract {
    public static final String BINARY = "6080604052348015600e575f80fd5b50610a338061001c5f395ff3fe608060405234801561000f575f80fd5b506004361061004a575f3560e01c80630b1e7f831461004e57806340731c241461007f578063bcc95407146100b0578063c7303c61146100ce575b5f80fd5b610068600480360381019061006391906103fc565b6100ea565b6040516100769291906104d6565b60405180910390f35b610099600480360381019061009491906103fc565b6101bd565b6040516100a79291906104d6565b60405180910390f35b6100b86102fc565b6040516100c59190610513565b60405180910390f35b6100e860048036038101906100e39190610658565b610307565b005b5f81815481106100f8575f80fd5b905f5260205f2090600202015f91509050805f018054610117906106cc565b80601f0160208091040260200160405190810160405280929190818152602001828054610143906106cc565b801561018e5780601f106101655761010080835404028352916020019161018e565b820191905f5260205f20905b81548152906001019060200180831161017157829003601f168201915b505050505090806001015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16905082565b60605f80805490508310610206576040517f08c379a00000000000000000000000000000000000000000000000000000000081526004016101fd90610746565b60405180910390fd5b5f838154811061021957610218610764565b5b905f5260205f2090600202015f015f848154811061023a57610239610764565b5b905f5260205f2090600202016001015f9054906101000a900473ffffffffffffffffffffffffffffffffffffffff16818054610275906106cc565b80601f01602080910402602001604051908101604052809291908181526020018280546102a1906106cc565b80156102ec5780601f106102c3576101008083540402835291602001916102ec565b820191905f5260205f20905b8154815290600101906020018083116102cf57829003601f168201915b5050505050915091509150915091565b5f8080549050905090565b5f60405180604001604052808381526020013373ffffffffffffffffffffffffffffffffffffffff16815250908060018154018082558091505060019003905f5260205f2090600202015f909190919091505f820151815f01908161036c919061092e565b506020820151816001015f6101000a81548173ffffffffffffffffffffffffffffffffffffffff021916908373ffffffffffffffffffffffffffffffffffffffff160217905550505050565b5f604051905090565b5f80fd5b5f80fd5b5f819050919050565b6103db816103c9565b81146103e5575f80fd5b50565b5f813590506103f6816103d2565b92915050565b5f60208284031215610411576104106103c1565b5b5f61041e848285016103e8565b91505092915050565b5f81519050919050565b5f82825260208201905092915050565b8281835e5f83830152505050565b5f601f19601f8301169050919050565b5f61046982610427565b6104738185610431565b9350610483818560208601610441565b61048c8161044f565b840191505092915050565b5f73ffffffffffffffffffffffffffffffffffffffff82169050919050565b5f6104c082610497565b9050919050565b6104d0816104b6565b82525050565b5f6040820190508181035f8301526104ee818561045f565b90506104fd60208301846104c7565b9392505050565b61050d816103c9565b82525050565b5f6020820190506105265f830184610504565b92915050565b5f80fd5b5f80fd5b7f4e487b71000000000000000000000000000000000000000000000000000000005f52604160045260245ffd5b61056a8261044f565b810181811067ffffffffffffffff8211171561058957610588610534565b5b80604052505050565b5f61059b6103b8565b90506105a78282610561565b919050565b5f67ffffffffffffffff8211156105c6576105c5610534565b5b6105cf8261044f565b9050602081019050919050565b828183375f83830152505050565b5f6105fc6105f7846105ac565b610592565b90508281526020810184848401111561061857610617610530565b5b6106238482856105dc565b509392505050565b5f82601f83011261063f5761063e61052c565b5b813561064f8482602086016105ea565b91505092915050565b5f6020828403121561066d5761066c6103c1565b5b5f82013567ffffffffffffffff81111561068a576106896103c5565b5b6106968482850161062b565b91505092915050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52602260045260245ffd5b5f60028204905060018216806106e357607f821691505b6020821081036106f6576106f561069f565b5b50919050565b7f496e76616c696420696e646578000000000000000000000000000000000000005f82015250565b5f610730600d83610431565b915061073b826106fc565b602082019050919050565b5f6020820190508181035f83015261075d81610724565b9050919050565b7f4e487b71000000000000000000000000000000000000000000000000000000005f52603260045260245ffd5b5f819050815f5260205f209050919050565b5f6020601f8301049050919050565b5f82821b905092915050565b5f600883026107ed7fffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffffff826107b2565b6107f786836107b2565b95508019841693508086168417925050509392505050565b5f819050919050565b5f61083261082d610828846103c9565b61080f565b6103c9565b9050919050565b5f819050919050565b61084b83610818565b61085f61085782610839565b8484546107be565b825550505050565b5f90565b610873610867565b61087e818484610842565b505050565b5b818110156108a1576108965f8261086b565b600181019050610884565b5050565b601f8211156108e6576108b781610791565b6108c0846107a3565b810160208510156108cf578190505b6108e36108db856107a3565b830182610883565b50505b505050565b5f82821c905092915050565b5f6109065f19846008026108eb565b1980831691505092915050565b5f61091e83836108f7565b9150826002028217905092915050565b61093782610427565b67ffffffffffffffff8111156109505761094f610534565b5b61095a82546106cc565b6109658282856108a5565b5f60209050601f831160018114610996575f8415610984578287015190505b61098e8582610913565b8655506109f5565b601f1984166109a486610791565b5f5b828110156109cb578489015182556001820191506020850194506020810190506109a6565b868310156109e857848901516109e4601f8916826108f7565b8355505b6001600288020188555050505b50505050505056fea2646970667358221220b37494dd8e44d3736a6a819371920e3a93456eb2e5c90d37f24b107fb862b72a64736f6c634300081a0033";

    private static String librariesLinkedBinary;

    public static final String FUNC_CREATEPOST = "createPost";

    public static final String FUNC_GETPOST = "getPost";

    public static final String FUNC_GETPOSTCOUNT = "getPostCount";

    public static final String FUNC_POSTS = "posts";

    @Deprecated
    protected PostStorage(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    protected PostStorage(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, credentials, contractGasProvider);
    }

    @Deprecated
    protected PostStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        super(BINARY, contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    protected PostStorage(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        super(BINARY, contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public RemoteFunctionCall<TransactionReceipt> createPost(String content) {
        final Function function = new Function(
                FUNC_CREATEPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.Utf8String(content)), 
                Collections.<TypeReference<?>>emptyList());
        return executeRemoteCallTransaction(function);
    }

    public RemoteFunctionCall<Tuple2<String, String>> getPost(BigInteger index) {
        final Function function = new Function(FUNC_GETPOST, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(index)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    public RemoteFunctionCall<BigInteger> getPostCount() {
        final Function function = new Function(FUNC_GETPOSTCOUNT, 
                Arrays.<Type>asList(), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Uint256>() {}));
        return executeRemoteCallSingleValueReturn(function, BigInteger.class);
    }

    public RemoteFunctionCall<Tuple2<String, String>> posts(BigInteger param0) {
        final Function function = new Function(FUNC_POSTS, 
                Arrays.<Type>asList(new org.web3j.abi.datatypes.generated.Uint256(param0)), 
                Arrays.<TypeReference<?>>asList(new TypeReference<Utf8String>() {}, new TypeReference<Address>() {}));
        return new RemoteFunctionCall<Tuple2<String, String>>(function,
                new Callable<Tuple2<String, String>>() {
                    @Override
                    public Tuple2<String, String> call() throws Exception {
                        List<Type> results = executeCallMultipleValueReturn(function);
                        return new Tuple2<String, String>(
                                (String) results.get(0).getValue(), 
                                (String) results.get(1).getValue());
                    }
                });
    }

    @Deprecated
    public static PostStorage load(String contractAddress, Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return new PostStorage(contractAddress, web3j, credentials, gasPrice, gasLimit);
    }

    @Deprecated
    public static PostStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, BigInteger gasPrice, BigInteger gasLimit) {
        return new PostStorage(contractAddress, web3j, transactionManager, gasPrice, gasLimit);
    }

    public static PostStorage load(String contractAddress, Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return new PostStorage(contractAddress, web3j, credentials, contractGasProvider);
    }

    public static PostStorage load(String contractAddress, Web3j web3j,
            TransactionManager transactionManager, ContractGasProvider contractGasProvider) {
        return new PostStorage(contractAddress, web3j, transactionManager, contractGasProvider);
    }

    public static RemoteCall<PostStorage> deploy(Web3j web3j, Credentials credentials,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PostStorage.class, web3j, credentials, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PostStorage> deploy(Web3j web3j, Credentials credentials,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PostStorage.class, web3j, credentials, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

    public static RemoteCall<PostStorage> deploy(Web3j web3j, TransactionManager transactionManager,
            ContractGasProvider contractGasProvider) {
        return deployRemoteCall(PostStorage.class, web3j, transactionManager, contractGasProvider, getDeploymentBinary(), "");
    }

    @Deprecated
    public static RemoteCall<PostStorage> deploy(Web3j web3j, TransactionManager transactionManager,
            BigInteger gasPrice, BigInteger gasLimit) {
        return deployRemoteCall(PostStorage.class, web3j, transactionManager, gasPrice, gasLimit, getDeploymentBinary(), "");
    }

//    public static void linkLibraries(List<Contract.LinkReference> references) {
//        librariesLinkedBinary = linkBinaryWithReferences(BINARY, references);
//    }

    private static String getDeploymentBinary() {
        if (librariesLinkedBinary != null) {
            return librariesLinkedBinary;
        } else {
            return BINARY;
        }
    }

	
}
