package com.zhangweikang.www.serializer;

import io.protostuff.LinkedBuffer;
import io.protostuff.ProtobufIOUtil;
import io.protostuff.Schema;
import io.protostuff.runtime.RuntimeSchema;

/**
 * protosbuf序列化
 *
 * 1.压缩字节,通过varint算法
 *      例:int有4个字节,32位
 *          先取后7位,如果当前7位前面还有数字,则高位补为1,该高位在后续的的计算中不作任何计算只做标记,继续向前取7位,已当前规则拼接字节
 *                  如果当前7位前没有任何数字,高位补0,标示这是最后一个字节的数字
 *          带符号的数字,通过zigzag算法压缩字节
 *              例:-2(一个32个字节的负数)
 *              将字节向左移动一位,末尾补0
 *              将左移的字节,向右移动31位
 *              将左移后的字节与右移后的字节,进行亦或(^),得出一个正数
 *              将亦或(^)后得出的数字,再用varint编码压缩
 * 2.利用T-L-V(Tag-Length-Value)存储
 *  tag:标示字段
 *      其中包含两个字段:
 *      field_number:标示,字段.id=后的数字,如果不指定将会随机生成
 *      wire_type:字段类型
 *  Tag  = (field_number << 3) | wire_type
 *      理论上来讲,两个标示会需要占用2个字节
 *      1.一个字节表示tag,field_number范围1-15
 *      但是wire_type总共有5种类型,也就是0-5,二进制,只会占用3个byte位
 *      field_number二进制位移不会影响结果,
 *      所以将field_number<<3,后面3位由wire_type的3位填充,节约空间
 *      2.两个字节表示Tag,field_number范围16-2047
 *      10000000 00000000
 *      第一个字节的最高位和第二个字节的最高位为标志位
 *      第一个字节的最高位 一定为1
 *      第二个字节的最高位 一定为0
 *
 *      剔除两个标志位,最后的3位位移位,还剩下11位,也就是最高2047
 *  length:数据长队
 *      通过varint&zigzag编码的存储方式没有length这一列
 *  value:编码后的值
 *      通过varint&zigzag编码的值
 *
 *
 *
 * @author ZhangWeiKang
 * @create 2018/6/2
 */
public class ProtosbufSerializer implements ISerializer {
    @Override
    public <T>byte[] serializer(T t) {
        Schema schema = RuntimeSchema.getSchema(t.getClass());
        return ProtobufIOUtil.toByteArray(t, schema,  LinkedBuffer.allocate(256));
    }

    @Override
    public <T> T deserializer(byte[] data, Class<T> clazz) {

        T t;
        try {
            t = clazz.newInstance();
            Schema<T> schema = RuntimeSchema.getSchema(clazz);
            ProtobufIOUtil.mergeFrom(data,t,schema);
            return t;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
