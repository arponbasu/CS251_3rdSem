from django.db import models

# Create your models here.
from django.contrib.auth.models import User
from django.db.models.signals import post_save
from django.dispatch import receiver
from django.utils.timezone import now
from django.contrib.postgres.fields import ArrayField


class Profile(models.Model):
    user = models.OneToOneField(User, on_delete=models.CASCADE)
    NumFollowers = models.IntegerField(default=-1)
    LastUpdated = models.DateTimeField(default=now)
    AllRepos = models.CharField(max_length=1000, default="|")

@receiver(post_save, sender=User)
def create_user_profile(sender, instance, created, **kwargs):
    if created:
        Profile.objects.create(user=instance)

@receiver(post_save, sender=User)
def save_user_profile(sender, instance, **kwargs):
    instance.profile.save()
