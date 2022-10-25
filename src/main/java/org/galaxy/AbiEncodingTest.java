package org.galaxy;

import org.junit.jupiter.api.Test;
import org.web3j.abi.TypeEncoder;
import org.web3j.abi.datatypes.Address;
import org.web3j.abi.datatypes.DynamicStruct;
import org.web3j.abi.datatypes.Uint;
import org.web3j.abi.datatypes.Utf8String;

import java.math.BigInteger;

import static org.junit.jupiter.api.Assertions.assertEquals;

// https://ethereum.stackexchange.com/questions/106740/get-the-same-result-of-abi-encode-function-in-java
// function tryAbiEncode (address  _address, uint8 _amount) public pure returns (bytes memory) {
//     return abi.encode("\x19Ethereum Signed Message:\n",_address, _amount);
// }

public class AbiEncodingTest {

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
    }

}
