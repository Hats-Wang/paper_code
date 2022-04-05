# paper usage

```
gradle 6.6.1

JDK 1.8.0_202

solc 0.5.16+commit.9c3226ce.Linux.g++

web3sdk 2.6.4
```


搭建区块链教程: https://fisco-bcos-documentation.readthedocs.io/zh_CN/v2.8.0/docs/installation.html#fisco-bcos

使用说明: cp ~/fisco/nodes/127.0.0.1/sdk/*  paper/src/main/resources



命令参数输入参考如下！

1. 合约部署参数： deploy keyStoreFileName keyStorePassword keyPassword 
```
deploy user.jks 123456 123456
```
返回合约地址：
```
deploy SignersData Contract success, address: 0x0f74e2529e561b2749034a3d235da8cf04ab8bea
deploy Mortgage Contract success, address: 0xe9ed8526260796ee667e7217b239fdb357fcdfc5
```

2.创建新公司信用参数： new  keyStoreFileName keyStorePassword keyPassword deployAddress grade companyName nameHash pledge companyValue 
```
new user.jks 123456 123456 0x0f74e2529e561b2749034a3d235da8cf04ab8bea 90 com 0x4D236D9A2D102C5FE6AD1C50DA4BEC50 false 45689
```
返回credit地址：
```
newCredit success, newCreditAddress: 0xf55b09f836195382cb3e254d5d5be63f9eae6110
```

3. 发送签名参数： send keyStoreFileName keyStorePassword keyPassword newCreditAddress
```
send arbitrator.jks 123456 123456 0xf55b09f836195382cb3e254d5d5be63f9eae6110
send depositor.jks  123456 123456 0xf55b09f836195382cb3e254d5d5be63f9eae6110
```
返回成功与否：
```
sendSignatureToBlockChain success！true / sendSignatureToBlockChain failed！false
```

4. 获取证据参数： get keyStoreFileName keyStorePassword keyPassword newCreditAddress
```
get user.jks 123456 123456 0xf55b09f836195382cb3e254d5d5be63f9eae6110
```
返回公司信贷信息：
```
the CompanyName: com
the credit grade of company: 90
the CompanyValue: 45689
the Company is not pledged.
the signature[0]: 1bf6de8b8a17faaed36498bb8dc68dc5c4e2ad7306a3b2717bf1ff96319698fd8d4e0f74964f97c247913c00fd917e9a378898c82f03c5f7c3c8623a314519ca99
the signature[1]: 1bc9346e56d3abc5f3249977a95f888a49fbbd8f4eea1cd1a80f3d362caf4392d16b6e021eb80ab8db21a5cf8673915e5f1015c4825d48a3f7d51e22dd0e153987
the signature[2]: 1b2f02099676a9da742db3445bd8ef130e6521bd15bf32bf56f8721bbe502526b37e775907772af96e5d6c1250f9b1d47142ac6f88f0e8f9de1ab203d153b1ea51
the publicKey[0]: 0x33674063c4618f4773fac75dc2f07e55f6f391ce
the publicKey[1]: 0x6bc952a2e4db9c0c86a368d83e9df0c6ab481102
the publicKey[2]: 0x5a6c7ccf9efa702f4e8888ff7e8a3310abcf8c51
```

5. 证据和签名校验参数： verify keyStoreFileName keyStorePassword keyPassword newCreditAddress
```
verify user.jks 123456 123456 0xf55b09f836195382cb3e254d5d5be63f9eae6110
```
返回验证是否成功：
```
verifyCredit success！true / verifyCredit failed！false
```

6. 获取公钥参数： getPublicKey keyStoreFileName keyStorePassword keyPassword
```
getPublicKey user.jks 123456 123456
```
返回公钥信息：
```
publicKey:0x33674063c4618f4773fac75dc2f07e55f6f391ce
```

7. 抵押公司参数： mortgage keyStoreFileName keyStorePassword keyPassword newCreditAddress MortgageAddress loan_num 
```
mortgage user.jks 123456 123456 0xf55b09f836195382cb3e254d5d5be63f9eae6110  0xe9ed8526260796ee667e7217b239fdb357fcdfc5 99
```
返回公司是否抵押成功:
```
company is mortgageed successfully! / company is mortgaged abortively!
```

8. 赎回公司
redeem keyStoreFileName keyStorePassword keyPassword newCreditAddress MortgageAddress
```
redeem user.jks 123456 123456 0xf55b09f836195382cb3e254d5d5be63f9eae6110  0xe9ed8526260796ee667e7217b239fdb357fcdfc5
```