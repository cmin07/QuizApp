package cmin.quizapp;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.StorageException;
import com.microsoft.azure.storage.blob.CloudBlobClient;
import com.microsoft.azure.storage.blob.CloudBlobContainer;
import com.microsoft.azure.storage.blob.CloudBlockBlob;

import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.security.InvalidKeyException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Repository {
    public static final String StorageConnectionString = "DefaultEndpointsProtocol=https;"
            + "AccountName=dreamstudy;"
            + "AccountKey=NV+ttpT3qnE4f2HoRuM03NIqcAeKj0M4kkZvqnvviEfRxDu+C5r12OcmJUgYKudwsyih3VQDuXPzJW8RTzUioA==";

    public static final String StoragePersonContainer = "0926chong";

    private Gson gson = new Gson();

    public Response<List<LeaderBoardRank>> getLeaderBoardRank()  {
        try {
            CloudBlockBlob blob = getBlob();

            if (blob.exists()) {
                Type type = new TypeToken<ArrayList<LeaderBoardRank>>() {}.getType();
                Gson gson = new Gson();
                List<LeaderBoardRank> LeaderBoardRank = gson.fromJson(blob.downloadText(), type);

                return new Response.Success(LeaderBoardRank);
            } else {
                return new Response.NotExist();
            }
        } catch (Exception e) {
            return new Response.Failure();
        }
    }

    public Response<LeaderBoardRank> updatePerson(List<LeaderBoardRank> ranks)  {
        try {
            CloudBlockBlob blob = getBlob();

            blob.uploadText(gson.toJson(ranks));
            return new Response.Success(ranks);
        } catch (Exception e) {
            return new Response.Failure();
        }
    }

    private CloudBlockBlob getBlob() throws URISyntaxException, StorageException, InvalidKeyException {
        CloudStorageAccount account = CloudStorageAccount.parse(StorageConnectionString);
        CloudBlobClient blobClient = account.createCloudBlobClient();
        CloudBlobContainer cloudBlobContainer = blobClient.getContainerReference(StoragePersonContainer);
        return cloudBlobContainer.getBlockBlobReference("rank");
    }
}