package helo.mali.sneakerverse;

import android.content.Context;
import android.support.annotation.NonNull;

import java.util.List;

import androidx.work.Worker;
import androidx.work.WorkerParameters;
import helo.mali.sneakerverse.sneakers.Sneakers;
import helo.mali.sneakerverse.sneakers.SneakersRepository;


public class SyncWorker extends Worker {
    private SneakersRepository sneakersRepository;

    public SyncWorker(@NonNull Context context, @NonNull WorkerParameters workerParams) {
        super(context, workerParams);
        sneakersRepository = new SneakersRepository(context);
    }

    @NonNull
    @Override
    public Result doWork() {
        List<Sneakers> backendSneakers = getBackendSneakers();
        // TODO: Save latest sneakers from the backend
        // sneakersRepository.save(backEndSneakers);
        return Result.success();
    }

    private List<Sneakers> getBackendSneakers(){
        return null;
    }
}
