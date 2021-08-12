package com.callor.myword.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.callor.myword.model.WordDTO;
import com.callor.myword.dao.WordDao;

import java.util.List;

/*
 * WordDao와 WordDTO를 사용하여 DataBase Handling을 하는 중간 Service Class
 */
public class WordRepository {

    protected WordDao wordDao;
    protected LiveData<List<WordDTO>> wordList;

    /*
     * Application Context를 매개변수로 갖는 생성자 필요
     *
     * @param application
     */

    public WordRepository(Application application) {
    }

    /*
     * wordList를 단순히 return하고 있는 데 wordList 데이터를 가져오는 코드는 Where...??
     *
     * 외부에서 repository.selectAll() method를 호출하면
     * 내부에서 자동으로 Dao를 거쳐 DB에 Select를 수행하고 그 결과를 return한다
     *
     * @return
     */
    public LiveData<List<WordDTO>> selectAll() {
        return wordList;
    }

    public void insert(WordDTO wordDTO){
        wordDao.insert(wordDTO);
    }

    public void deletAll() {
        wordDao.deleteAll();
    }
}
