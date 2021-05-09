package presentation.di;

import dagger.Component;
import domain.common.ADBHelper;
import presentation.presenter.HotkeyPresenter;
import presentation.presenter.MenuPresenter;
import presentation.presenter.MobilePresenter;

import javax.inject.Singleton;

@Component(modules = AppModule.class)
@Singleton
public interface Injector {

    void injectMobilePresenter(MobilePresenter presenter);

    void injectMenuPresenter(MenuPresenter presenter);

    void injectHotkeyPresenter(HotkeyPresenter presenter);

    ADBHelper injectADBHelper();
}
