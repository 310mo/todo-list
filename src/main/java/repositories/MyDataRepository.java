package com.example.demo.repositories;
 
import com.example.demo.MyData;
import java.util.Optional;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
 
@Repository
public interface MyDataRepository extends JpaRepository<MyData, Long> {

    public Optional<MyData> findById(int name);

    public List<MyData> findByContentLikeOrCategory(String content, String category);

    public List<MyData> findByContentLike(String content);

    public Optional<MyData> findByCategory(String category);

}