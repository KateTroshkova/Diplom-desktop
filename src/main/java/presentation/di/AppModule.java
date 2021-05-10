package presentation.di;

import dagger.Module;
import dagger.Provides;
import data.database.converter.HotkeyMapper;
import data.database.dao.DesktopActionDao;
import data.database.dao.HotkeyDao;
import data.database.dao.MobileActionDao;
import data.repository.ConnectionRepository;
import data.repository.DatabaseRepository;
import data.repository.MobileRepository;
import domain.api.ConnectionRepositoryApi;
import domain.api.DatabaseRepositoryApi;
import domain.api.MobileRepositoryApi;
import domain.common.ADBHelper;
import domain.interactor.*;

import javax.inject.Singleton;

@Module
public class AppModule {

    @Provides
    @Singleton
    public ConnectionRepositoryApi provideConnectionRepositoryApi() {
        return new ConnectionRepository();
    }

    @Provides
    @Singleton
    public DatabaseRepositoryApi provideDatabaseRepositoryApi() {
        return new DatabaseRepository(provideHotkeyDao(), provideMobileActionDao(), provideDesktopActionDao(), provideHotkeyMapper());
    }

    @Provides
    @Singleton
    public MobileRepositoryApi provideMobileRepository() {
        return new MobileRepository(provideADBHelper());
    }

    @Provides
    @Singleton
    public ADBHelper provideADBHelper() {
        return new ADBHelper();
    }

    @Provides
    @Singleton
    public VideoInteractor provideVideoInteractor() {
        return new VideoInteractor(provideMobileRepository());
    }

    @Provides
    @Singleton
    public ConnectionInteractor provideConnectionInteractor(){
        return new ConnectionInteractor(provideConnectionRepositoryApi());
    }

    @Provides
    @Singleton
    public EventInteractor provideEventInteractor(){
        return new EventInteractor(provideMobileRepository());
    }

    @Provides
    @Singleton
    public GestureDetector provideGestureDetector(){
        return new GestureDetector();
    }

    @Provides
    @Singleton
    public HotkeyInteractor provideHotkeyInteractor(){
        return new HotkeyInteractor(provideDatabaseRepositoryApi());
    }

    @Provides
    @Singleton
    public HotkeyMapper provideHotkeyMapper() {
        return new HotkeyMapper();
    }

    @Provides
    @Singleton
    public DesktopActionDao provideDesktopActionDao() {
        return new DesktopActionDao();
    }

    @Provides
    @Singleton
    public HotkeyDao provideHotkeyDao() {
        return new HotkeyDao();
    }

    @Provides
    @Singleton
    public MobileActionDao provideMobileActionDao() {
        return new MobileActionDao();
    }
}
