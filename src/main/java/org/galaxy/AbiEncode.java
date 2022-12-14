package org.galaxy;

import org.junit.jupiter.api.Test;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;
import org.web3j.abi.datatypes.generated.Bytes32;
import org.web3j.abi.datatypes.generated.Uint256;
import org.web3j.abi.datatypes.generated.Uint64;
import org.web3j.crypto.Hash;
import org.web3j.utils.Numeric;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://ethereum.stackexchange.com/questions/106740/get-the-same-result-of-abi-encode-function-in-java
// function tryAbiEncode (address  _address, uint8 _amount) public pure returns (bytes memory) {
//     return abi.encode("\x19Ethereum Signed Message:\n",_address, _amount);
// }

public class AbiEncode {

    @Test
    public void testAbiEncoding() {
        String result = "0x" +
                TypeEncoder.encode(new DynamicStruct(
                        new Utf8String("\u0019Ethereum Signed Message:\n"),
                        new Address("0xbfF89Fe7598f162ACC86CfC3267Eb132F69B7e2B"),
                        new Uint(BigInteger.valueOf(10))));
        assertEquals("0x" +
                "0000000000000000000000000000000000000000000000000000000000000060" +
                "000000000000000000000000bff89fe7598f162acc86cfc3267eb132f69b7e2b" +
                "000000000000000000000000000000000000000000000000000000000000000a" +
                "000000000000000000000000000000000000000000000000000000000000001a" +
                "19457468657265756d205369676e6564204d6573736167653a0a000000000000", result);

        Bytes32 node = new Bytes32(
                Numeric.hexStringToByteArray(
                        "0xc6cbe29b02227ba1bb49c0da438c639867e06abe8377a4e69e75a8b705b17b10"
                )
        );

        final long date = (System.currentTimeMillis() / 1000 / (24 * 60 * 60));

        String dataForSign = "0x" +
                TypeEncoder.encode(
                        new DynamicStruct(
                            new Uint64(BigInteger.valueOf(date)),
                            node,
                            new Uint256(new BigInteger("1600000000000000"))
                        )
                );

        System.out.println(dataForSign);

        String ret = Numeric.toHexString(Numeric.hexStringToByteArray(dataForSign));
        System.out.println(ret);
        String hash = Hash.sha3(dataForSign);
        System.out.println(hash);


    }



}

/*

public static String sha3(String hexInput) {
    byte[] bytes = Numeric.hexStringToByteArray(hexInput);
    byte[] result = sha3(bytes);
    return Numeric.toHexString(result);
}


https://nullbeans.com/what-is-a-biginteger-and-how-to-use-it-in-java/

BigIntegers are Immutable
Notice that each time you perform an arithmetic operation, a new BigInteger instance is produced.
This is because already instantiated instances of BigIntegers are immutable.
In other words, once you have created an instance, you cannot change the value of that instance.
One can only assume that this was done by the Java creators because it was simpler to implement and less error-prone,
However, this also comes at the cost of memory as for each new instance, a new place in the JVM memory is reserved.

What is the maximum size of a BigInteger in Java

The officially supported range of values for Big integers is -[(2^Integer.MAX_VALUE)-1] to +[(2Integer.MAX_VALUE) -1] .
However, larger values are theoretically possible and are technically limited to the amount of memory the Java virtual machine has.
You can read more about this in the official documentation. https://docs.oracle.com/javase/8/docs/api/java/math/BigInteger.html
In other words, there is official support for the given range of numbers in BigIntegers, however,
you can unofficially use it for even larger numbers if you have enough computing resources for it.

*/


