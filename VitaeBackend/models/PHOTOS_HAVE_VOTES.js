/* jshint indent: 2 */

module.exports = function(sequelize, DataTypes) {
  return sequelize.define('PHOTOS_HAVE_VOTES', {
    photo_id: {
      type: DataTypes.INTEGER(11),
      allowNull: false,
      references: {
        model: 'PHOTOS',
        key: 'photo_id'
      }
    },
    user_id: {
      type: DataTypes.STRING,
      allowNull: false,
      references: {
        model: 'USERS',
        key: 'user_id'
      }
    }
  }, {
    tableName: 'PHOTOS_HAVE_VOTES'
  });
};
