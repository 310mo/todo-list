package com.example.demo.repository;
 
import com.example.demo.entity.MyData;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    public Optional<MyData> findById(int name);

    public List<MyData> findByContentLikeOrCategory(String content, String category);

    public List<MyData> findByContentLike(String content);

    public List<MyData> findByCategory(String category);

    public List<MyData> findByIsdone(boolean isdone);

}