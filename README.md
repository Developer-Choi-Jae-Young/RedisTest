# RedisTest
RedisTest

redis-cli 접속
호스트명과 포트번호를 생략하면 localhost의 6379로 접속됩니다.

-n db번호

-a 비밀번호

-s 소켓

-u 서버 url 등 접속 시 다양한 옵션 설정이 사용 가능합니다.

    [localhost:6379접속]
    redis-cli

    [원격접속]
    redis-cli -h #{호스트명} -p #{포트번호}

    [정보보기]
    reids-cli info

    [help]
    redis-cli help

# 모니터링
redis-cli monitor
CRUD 명령어
Keys * 현재의 키값들을 확인할 수 있습니다. (데이터가 많은 경우 부하가 심하기 때문에 운영 중인 서비스에선 주의가 필요합니다)

(empty list or set) 은 현재는 저장된 키값이 없을 경우 출력됩니다.

    127.0.0.1:6379> keys *
    (empty list or set)
    
set key / value 형태로 저장하기

    127.0.0.1:6379>set k_one v_one
    OK

    127.0.0.1:6379>keys *
    1) "k_one"
mset 여러개의 key / value 형태로 저장하기

    127.0.0.1:6379> mset k_two v_two k_tree v_tree
    OK

    127.0.0.1:6379> keys *
    1) "k_tree"
    2) "k_one"
    3) "k_two"
    
setex 소멸시간 지정해서 저장하기

key second value  시간은 초단위로 입력해야합니다.

    127.0.0.1:6379> setex k_four 10 k_four
    OK

get key에 해당하는 value를 조회하기

(nil) 은 해당 key가 없을 경우 출력됩니다.

    127.0.0.1:6379> get k_four
    (nil)
    127.0.0.1:6379> get k_tree
    "v_tree"

mget 여러개의 key를 조회하기

    127.0.0.1:6379> mget k_one k_two
    1) "v_one"
    2) "v_two"

del 해당 key와 value을 삭제하기

(integer) 1은 삭제성공

(integer) 0은 해당 key가 없을 경우 출력됩니다.

    127.0.0.1:6379> del k_tree
    (integer) 1

    127.0.0.1:6379> keys *
    1) "k_one"
    2) "k_two"

ttl 타임아웃까지 남은 시간을 초단위로 반환

pttl 타임아웃까지 남은 시간을 밀리 초단위로 반환

(integer) -2 는 key값이 없거나 소멸된 경우 출력됩니다.

(integer) -1 는 기한이 없는경우 출력됩니다.

    127.0.0.1:6379> setex k_tree 60 v_tree
    OK

    127.0.0.1:6379> ttl k_tree
    (integer) 54

    127.0.0.1:6379> pttl k_tree
    (integer) 48628

    127.0.0.1:6379> ttl k_tree
    (integer) -2

    127.0.0.1:6379> ttl k_one
    (integer) -1
 

keys *검색어*  key 검색하기

해당 검색어가 포함된 모든 key를 검색합니다.

    127.0.0.1:6379> keys *k*
    1) "k_one"
    2) "k_two"
 

rename key의 이름을 변경하기 rename 기존key 변경할key

key가 존재 할경우 덮어씁니다.

renamenx key의 이름을 변경하기 nenamenx 기본key 변경할key

(integer)은 해당 key가 존재할 경우 출력됩니다.

    127.0.0.1:6379> rename k_one one
    OK

    127.0.0.1:6379> keys *
    1) "one"
    2) "k_two"

    127.0.0.1:6379> renamenx k_two one
    (integer) 0

    127.0.0.1:6379> keys *
    1) "one"
    2) "k_two"

flushall 모든 데이터(key와 value)를 삭제

    127.0.0.1:6379> flushall
    OK

    127.0.0.1:6379> keys *
    (empty list or set)
