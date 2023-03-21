package com.aidos.roomy_app.frameworks.dagger.subcomponents

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.aidos.roomy_app.activities.AdministrationViewModel
import com.aidos.roomy_app.activities.MainViewModel
import com.aidos.roomy_app.frameworks.dagger.ViewModelFactory
import com.aidos.roomy_app.ui.admin_ui.AdminMenuViewModel
import com.aidos.roomy_app.ui.admin_ui.MakeAnnouncementViewModel
import com.aidos.roomy_app.ui.admin_ui.RequestsViewModel
import com.aidos.roomy_app.ui.resident_ui.*
import dagger.Binds
import dagger.Module
import dagger.multibindings.IntoMap
@Module
abstract class ViewModelModule {
    @Binds
    abstract fun bindViewModelFactory(viewModelFactory: ViewModelFactory): ViewModelProvider.Factory

    @Binds
    @IntoMap
    @ViewModelKey(MainViewModel::class)
    abstract fun bindMainViewModel(mainViewModel: MainViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdministrationViewModel::class)
    abstract fun bindAdministrationViewModel(adminViewModel: AdministrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(LoginViewModel::class)
    abstract fun bindLoginViewModel(loginViewModel: LoginViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RegistrationViewModel::class)
    abstract fun bindRegistrationViewModel(registrationViewModel: RegistrationViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(DormitoriesViewModel::class)
    abstract fun bindDormitoriesViewModel(dormitoriesViewModel: DormitoriesViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RequestsViewModel::class)
    abstract fun bindRequestsViewModel(requestsViewModel: RequestsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MakeAnnouncementViewModel::class)
    abstract fun bindMakeAnnouncementViewModel(makeAnnouncementViewModel: MakeAnnouncementViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AdminMenuViewModel::class)
    abstract fun bindAdminMenuViewModel(adminMenuViewModel: AdminMenuViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(RoomsViewModel::class)
    abstract fun bindRoomsViewModel(roomsViewModel: RoomsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(BookingViewModel::class)
    abstract fun bindBookingViewModel(bookingViewModel: BookingViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(AnnouncementsViewModel::class)
    abstract fun bindAnnouncementsViewModel(announcementsViewModel: AnnouncementsViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(MyRoomViewModel::class)
    abstract fun bindMyRoomViewModel(myRoomViewModel: MyRoomViewModel): ViewModel

    @Binds
    @IntoMap
    @ViewModelKey(ProfileViewModel::class)
    abstract fun bindProfileViewModel(profileViewModel: ProfileViewModel): ViewModel

}