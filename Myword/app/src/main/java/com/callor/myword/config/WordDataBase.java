package com.callor.myword.config;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.callor.myword.dao.WordDao;
import com.callor.myword.model.WordDTO;

/*
 * RoomDatabase를 상속받아 DB Connection, Session을 만드는 Class
 *
 * 이 Class는 반드시 abstract 키워드를 추가하여 추상클래스로 선언을 해야 한다.
 *
 * 추상 Class
 *  일부는 직접 코드를 구현하는 method를 포함하고
 *  일부는 Interface처럼 코드가 구현되지 않은 method를 함께 포함하는
 *  Class
 */

/*
 *  @Database(entities = {WordDTO.class}, version = 1)
 *
 *  만약 DB table이 없으면 WordDTO Class를 참조하여 table을 만들어라
 *
 *  version 항목의 내용
 *  혹시 사용과정에서 table에 변경 사항이 발생할 수 있다
 *  여기서는 table에 변경이 이루어지면 WordDTO 클래스에 칼럼들을 변경하는 작업을 수행한다
 *  그리고, version num을 현재 값보다 큰 값으로 변경한다
 *  변경된 version num을 기준으로 새롭게 table을 재구성한다
 */
@Database(entities = {WordDTO.class}, version = 1)
public abstract class WordDataBase extends RoomDatabase {

    static WordDataBase dbConn;

    // db가 없으면 새로 table을 만들고 있으면 db를 return해준다.
    // 데이터 관련 코드에서 사용할 DB Connection(Session) 객체를 return하는 method
    public static WordDataBase getDataBase(final Context context){
        if (dbConn == null) {
            // callback을 추가했다..? --> eventHandler가 있을 것 같다!
            dbConn = Room.databaseBuilder(
                    context.getApplicationContext(),
                    WordDataBase.class,
                    "word_database"
            ).addCallback(null).build();
        }
        return dbConn;
    }

    /*
     * 시스템이 생성하여 Dao를 만드는 코드로 재작성될 것이다
     *
     * @return
     */
    abstract WordDao getWordDao();
}
